package ar.com.nny.base.configuration;

import org.hibernate.Session;

public interface FilterEnabler {
	void enableFilters(Session session);
}
