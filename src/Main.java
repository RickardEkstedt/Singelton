public class Main {
    public static void main(String[] args) {
        ConfigSingleton configSingleton = ConfigSingleton.getInstance();

        String nameValue = configSingleton.read("name");
        System.out.println("Värdet för 'name': " + nameValue);

        configSingleton.save("name", "Tom");

        String updatedNameValue = configSingleton.read("name");
        System.out.println("Uppdaterat värde för 'name': " + updatedNameValue);
    }
}