import java.util.ArrayList;
import java.util.Random;

public class FarmDriverMBruckhausPeriod3 {


    public static void main(String[] args) {
        Farm farm = new Farm();
        farm.animalSounds();
    }
}

interface Animal {
    public String getSound();
    public String getType();
}

class Farm {
    private ArrayList<Animal> myFarm;

    public Farm() {
        myFarm = new ArrayList<>();
        myFarm.add(new Cow());
        myFarm.add(new Chick(true));
        myFarm.add(new Pig());
        myFarm.add(new NamedCow("Elsie"));
    }

    public void animalSounds() {
        Animal temp;
        for (int i = 0; i < myFarm.size(); i++) {
            temp = myFarm.get(i);
            System.out.println(temp.getType() + " goes " + temp.getSound());
        }

        NamedCow named = (NamedCow) myFarm.get(3);
        System.out.println(named.getName());
    }
}

class Cow implements Animal {
    private String myType;
    private String mySound;

    Cow() {
        myType = "cow";
        mySound = "moo";
    }

    public String getSound() {
        return mySound;
    }

    public String getType() {
        return myType;
    }
}

class NamedCow extends Cow implements Animal {
    private String myType;
    private String mySound;
    private String myName = "";

    NamedCow() {
        myType = "cow";
        mySound = "moo";
    }

    NamedCow(String name) {
        myType = "cow";
        mySound = "moo";
        myName = name;
    }

    public String getSound() {
        return mySound;
    }

    public String getType() {
        return myType;
    }

    public String getName() {
        return myName;
    }

}

class Chick implements Animal {
    private String myType;
    private String mySound;
    private String myOtherSound;
    private boolean myTwoSoundFlag;

    Chick() {
        myType = "chick";
        mySound = "chick";
    }

    Chick(boolean soundFlag) {
        myType = "chick";
        myTwoSoundFlag = soundFlag;
        mySound = "cheep";
        myOtherSound = "cluck";
    }

    public String getSound() {
        Random r = new Random();
        if (myTwoSoundFlag && r.nextDouble() > 0.5) {
            return myOtherSound;
        } else {
            return mySound;
        }
    }

    public String getType() {
        return myType;
    }
}

class Pig implements Animal {
    private String myType;
    private String mySound;

    Pig() {
        myType = "pig";
        mySound = "oink";
    }

    public String getSound() {
        return mySound;
    }

    public String getType() {
        return myType;
    }
}