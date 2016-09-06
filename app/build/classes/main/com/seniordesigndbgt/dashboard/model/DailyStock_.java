package com.seniordesigndbgt.dashboard.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DailyStock.class)
public abstract class DailyStock_ {

	public static volatile SingularAttribute<DailyStock, String> symbol;
	public static volatile SingularAttribute<DailyStock, Long> id;
	public static volatile SingularAttribute<DailyStock, LocalDateTime> time;
	public static volatile SingularAttribute<DailyStock, Double> value;

}

