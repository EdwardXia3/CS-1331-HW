public class Zoo {

    public static void main(String[] args) {
        Animal[] animals = {new Animal("Chloe", Animal.Type.DOG),
                            new Animal("Fiona", Animal.Type.CAT),
                            new Animal("Wilbur", Animal.Type.COW)};
        for (Animal a: animals) {
            System.out.println(a.noise());
        }
    }
}