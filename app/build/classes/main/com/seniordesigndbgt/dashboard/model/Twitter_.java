package com.seniordesigndbgt.dashboard.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Twitter.class)
public abstract class Twitter_ {

	public static volatile SingularAttribute<Twitter, String> image;
	public static volatile SingularAttribute<Twitter, String> author;
	public static volatile SingularAttribute<Twitter, Timestamp> created;
	public static volatile SingularAttribute<Twitter, Long> id;
	public static volatile SingularAttribute<Twitter, String> text;
	public static volatile SingularAttribute<Twitter, String> url;

}

