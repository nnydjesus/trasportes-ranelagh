package ar.com.nny.base.configuration;

import java.util.List;

public class FlexySerializerResolverProvider implements SerializerResolverProvider {

    public List<Serializer> getSerializers() {
        return ApplicationRegistryReader.getInstance().getFlexSerializers();
    }

}
