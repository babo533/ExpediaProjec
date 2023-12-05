public abstract class Service {
    private String serviceID;
    private String serviceName;

    public Service(String serviceID, String serviceName) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
    }

    public abstract void getServiceDetails();

    public abstract void bookService();

    public String getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }



}
