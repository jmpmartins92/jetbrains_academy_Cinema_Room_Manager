class Application {

    String name;

    void run(String[] args) {
        System.out.println(name);
        for (String words : args) {
            System.out.println(words);
        }
    }
}