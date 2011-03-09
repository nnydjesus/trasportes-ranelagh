package ar.com.nny.base.configuration;

public interface ServiceLocatorStrategy {

    public <T> T locate(Class<T> serviceClass, boolean searchAmongNotFinishedOnes);

}
