package com.edu.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class EduNamingStrategy extends ImplicitNamingStrategyLegacyJpaImpl {

    //使用命名策略是为了防止字段名和数据库关键词冲突
    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier("edu_"+stringForm, buildingContext);
    }
}
