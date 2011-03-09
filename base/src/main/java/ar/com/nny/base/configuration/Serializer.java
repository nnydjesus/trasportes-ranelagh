package ar.com.nny.base.configuration;



/**
 * Just transform some java object into flex useful representation and backwards.
 * Serializers are context independant, they are just of transformation
 * algorithms.
 *
 * @author Claudio Fernandez
 */
public interface Serializer<T, D> {

    /**
     * Transform some object into flex representation.
     */
    public D serialize(T object, FlexConverter converter);


    /**
     * Returns true if an object of the given class can be serialized with
     * the current Serializer object. Class could be null (in null reference cases).
     */
    public boolean canSerialize(Class clazz);


    /**
     * Transform some object from flex representation into a java object.
     */
    public T deserialize(D object, TypedClass desiredType, FlexConverter converter, Object parent);

    /**
     * Additional actions to perform after deserialization and link with the parent object.
     */
    public void afterDeserialized(T object, final FlexConverter converter, Object parent);
    

    /**
     * Return true if an object of the given class could be deserialized
     * to addopt a desired class with the current Serializer object.
     */
    public boolean canDeserialize(Class clazz, TypedClass desiredType);


	public D notLoadedValue(T object, FlexConverter flexConverter);

}
