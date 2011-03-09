package ar.com.nny.base.configuration;

import java.io.*;
import java.util.Map;
import java.util.Map.Entry;


public abstract class AbstractMetaMapper {

    public abstract Map<String, String> getReplaceValues();


    public abstract String getMetaMappingFileName();


    public final String replaceValues(String line) {
        String ret = line;
        for ( Entry<String, String> entry : getReplaceValues().entrySet() ) {
            ret = ret.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return ret;
    }


    public final ByteArrayInputStream getMappingAsStream() throws IOException {
        String metaMappingFileName = this.getClass().getPackage().getName().replace('.', '/') + "/" + getMetaMappingFileName();
        InputStream inputStream = AbstractMetaMapper.class.getClassLoader().getResourceAsStream(metaMappingFileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer buffer = new StringBuffer(1024);
        String line = bufferedReader.readLine();
        while ( line != null ) {
            buffer.append(replaceValues(line));
            line = bufferedReader.readLine();
        }
        return new ByteArrayInputStream(buffer.toString().getBytes());
    }

}
