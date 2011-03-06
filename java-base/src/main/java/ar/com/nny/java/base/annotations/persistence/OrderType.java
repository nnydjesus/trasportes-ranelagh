package ar.com.nny.java.base.annotations.persistence;


public enum OrderType {

    ASC {
        @Override
        public String getOrderString(String fieldName) {
            return fieldName + " asc";
        }
    },
    DESC {
        @Override
        public String getOrderString(String fieldName) {
            return fieldName + " desc";
        }
    };


    public abstract String getOrderString(String fieldName);

}
