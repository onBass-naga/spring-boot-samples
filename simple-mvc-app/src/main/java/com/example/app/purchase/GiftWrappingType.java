package com.example.app.purchase;

import com.example.infra.persist.VarcharType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by naga on 2015/02/05.
 */
public class GiftWrappingType extends VarcharType {

    @Override
    public Class returnedClass() {
        return GiftWrapping.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        String value = (String) StringType.INSTANCE.get(rs, names[0], session);
        return value == null
                ? null : GiftWrapping.of(value);
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
