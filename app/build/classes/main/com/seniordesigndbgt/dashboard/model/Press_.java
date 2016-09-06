package com.seniordesigndbgt.dashboard.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Press.class)
public abstract class Press_ {

	public static volatile SingularAttribute<Press, String> sentiment;
	public static volatile SingularAttribute<Press, String> thumbnail;
	public static volatile SingularAttribute<Press, String> keywords;
	public static volatile SingularAttribute<Press, Integer> id;
	public static volatile SingularAttribute<Press, String> source;
	public static volatile SingularAttribute<Press, Date> time;
	public static volatile SingularAttribute<Press, String> title;
	public static volatile SingularAttribute<Press, String> body;
	public static volatile SingularAttribute<Press, String> url;

}

