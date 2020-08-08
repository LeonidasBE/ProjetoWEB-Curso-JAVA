import java.util.LinkedList;
import java.util.List;

class DeclarationDemo {

    void initPigeonPack() {
        int count = (int) Math.round(Math.abs(Math.random() * 1000));
        List<Pigeon> pigeonPack = new LinkedList<Pigeon>();
        for (int i = 0; i < count; i++) {
            pigeonPack.add(new Pigeon() {
                @Override
                public void consume(String trashTypeName) {
                    System.out.println("Pigeon is consuming " + trashTypeName);
                }
            });
        }
    }

    class PigeonBoss implements Pigeon {

        public void consume(String trashTypeName) {
            System.out.println("Pigeon boss consumes only a kebab.");
        }
    }

    interface Pigeon {
        void consume(String foodType);
    }
}