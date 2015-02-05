package com.example.infra.persist;

import com.example.app.purchase.GiftWrapping;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by naga on 2015/02/05.
 * Enumで使い回すために、無理矢理なことやってます。
 * 実装依存し過ぎでいつ壊れるかわからないので、使わないでください。。。よっぽどのことが無いかぎりは。
 */
public class EnumCodeType extends VarcharType {

    @Override
    public Class returnedClass() {
        return Enum.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        String value = (String) StringType.INSTANCE.get(rs, names[0], session);
        if (value == null) { return null; }

        String statSql = rs.getStatement().toString();
        Matcher m = Pattern.compile("( select )(.+?)( from )").matcher(statSql);
        m.find();

        Map<String, String> columns = Stream.of(m.group(2).split(","))
                .collect(Collectors.toMap(
                        it -> it.split(" as ")[1].trim(),
                        it -> it.split(" as ")[0].trim()));

        String column = columns.get(names[0]).split("\\.")[1];
        Field[] fields = owner.getClass().getDeclaredFields();
        Field target = Stream.of(fields)
                .filter(field -> field.getName().equalsIgnoreCase(column.replaceAll("_", "")))
                .findFirst()
                .get();

        try {
            Method method = target.getType().getMethod("of", String.class);
            return method.invoke(null, new Object[] {value});
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            StringType.INSTANCE.set(st, null, index, session);
        } else {
            StringType.INSTANCE.set(st, ((GiftWrapping)value).getCode(), index, session);
        }
    }
}
