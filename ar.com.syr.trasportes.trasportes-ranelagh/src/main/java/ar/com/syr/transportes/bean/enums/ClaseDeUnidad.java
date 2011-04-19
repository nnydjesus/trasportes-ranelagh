package ar.com.syr.transportes.bean.enums;

public enum ClaseDeUnidad {
    
    MEDIA("Media Distancia"),
    
    MEDIA_CORTA_LARGA("Media/Corta/Larga Distancia"),
    
    CORTA("Corta distancia"),
    
    LARGA("Larga distancia"),
    
    MAYORISTA("Mayorista"),
    
    DISTRIBUCION("Distribucion");
     
    private String name;
    private ClaseDeUnidad(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
