/*
 * author: Guilherme Coelho
 */
package br.com.fideliza.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	public static Session getSession() {
		try{
			AnnotationConfiguration configuration = new AnnotationConfiguration();
			configuration.configure();
			
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			return session;
		} catch (Throwable e){
			throw new ExceptionInInitializerError(e);
		}
	}
}

