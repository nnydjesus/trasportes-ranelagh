package ar.com.nny.base.configuration;

import ar.com.nny.base.initialData.DataGeneratorMethod;
import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;


public class CharacteristicGenerator<T extends AbstractCharacteristic> extends InitialDataGenerator<T> {

    private Class<T> characteristicClass;


    public CharacteristicGenerator(Class<? extends PersistentObject> persistentClass) {
        this.characteristicClass = (Class<T>) persistentClass;
        getDao();
    }


    protected CharacteristicDAO<T> getDao() {
        return (CharacteristicDAO<T>) DAOResolver.getDAOFor(characteristicClass);
    }


    @DataGeneratorMethod
    public void generate() {
        for ( CharacteristicValue valor : characteristicClass.getAnnotation(Characteristic.class).values() ) {
            T charact = ReflectionUtils.instanciate(characteristicClass);
            charact.setCode(valor.code());
            charact.setName(valor.name());

            if ( !"".equals(valor.codeSuperCharacteristic()) ) {
                getDao().getByCode(valor.codeSuperCharacteristic()).addSubCharacteristic(charact);
            }

            getDao().save(charact);
        }
    }
}
