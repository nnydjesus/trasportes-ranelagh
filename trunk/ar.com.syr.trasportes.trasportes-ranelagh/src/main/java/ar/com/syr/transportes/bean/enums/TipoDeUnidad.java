package ar.com.syr.transportes.bean.enums;

public enum TipoDeUnidad  {
    
    SEMI_REMOLQUE("Semi Remolque"),
    ACOPLADO("Acoplado"),
    TRACTOR("Tractor"),
    CHASIS("Chasis");
    
    private String name;
    private TipoDeUnidad(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }

}
