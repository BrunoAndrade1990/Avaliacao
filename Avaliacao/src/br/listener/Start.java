package br.listener;
import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.util.JpaUtil;


public class Start implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		EntityManager em = JpaUtil.getEntityManager();
	}

}
