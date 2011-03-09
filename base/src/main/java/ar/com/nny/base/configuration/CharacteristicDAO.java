package ar.com.nny.base.configuration;


public class CharacteristicDAO<T extends AbstractCharacteristic> extends ClasslessDAO<T> {

    public CharacteristicDAO(Class<T> persistentClass) {
        super(persistentClass);
    }


    public T getByCode(String code) {
      return (T) this.findUnique("code", code);
    }

}
