package ar.com.nny.base.configuration;

import java.util.Map;
import java.util.TreeMap;


public class CharacteristicMapper extends AbstractMetaMapper {

    private Class<? extends AbstractCharacteristic> clazz;


    private Characteristic characteristic;


    private String className;


    private String tableName;


    public CharacteristicMapper(Class<? extends AbstractCharacteristic> clazz) {
        this.clazz = clazz;
        this.characteristic = clazz.getAnnotation(Characteristic.class);
        this.className = clazz.getName();

        if ( "".equals(characteristic.tableName()) ) {
            this.tableName = clazz.getSimpleName();
        }
        else {
            this.tableName = characteristic.tableName();
        }
    }


    @Override
    public Map<String, String> getReplaceValues() {
        TreeMap<String, String> ret = new TreeMap<String, String>();

        ret.put("className", className);
        ret.put("tableName", tableName);

        return ret;
    }


    @Override
    public String getMetaMappingFileName() {
        return characteristic.allowSubCharacteristics() ? "SubCharacteristicsTemplate.hbm.xml" : "NoSubCharacteristicsTemplate.hbm.xml";
    }
}
