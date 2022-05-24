class Mammal():
    """Mammal() class is parent class to Gorilla() class and Bear() class. 
       It takes no parameters and sets genus and noise instance variables to null.
       Mammal() class has an overwritten function equal() that compares the genus of
       two different objects. It also has a listenToMammal() function that returns
       the genus of the object passed to the function and the noise from that certain class"""
       
    def __init__(self):
        """constructor of Mammal() class. instance variables set to null"""
        self.genus = ""
        self.noise = ""
      
    def equal(self, firstGenus, secondGenus):
        """overwritten equal() function that takes two parameters of the genus to be compared.
        These parameters come from the child classes of Mammal() class.
        it sets the the parameters passed to the class to the instance variables from the child classes 
        using the getGenus() functions from the child classes.
        The equal() function checks if the firstGenus equals to the secondGenus and returns if both
        parameters are equal or not equal"""
        
        """firstGenus and secondGenus local variables are set to the instance variables of their perspective 
        classes using the getGenus() functions of their class."""
        firstGenus = firstGenus.getGenus()
        secondGenus = secondGenus.getGenus()
        
        """if else statement comparing both genus. If equal it returns the firstGenus and secondGenus
        equal to each other. Else it returns not equal"""
        if(firstGenus == secondGenus):
            return print(firstGenus + " and " + secondGenus+ " are of the same genus")
        else:
            return print(firstGenus + " and " + secondGenus + " are *not* of the same genus")
            
    def listenToMammal(Mammal):
        """the listenToMammal() functions takes one parameter that is specific to one of the child classes.
        The function returns the genus and the noise made from the specific genus using the getGenus() and
        makeNoise() functions from their perspective classes"""
        print(Mammal.getGenus() + " is " + Mammal.makeNoise())
            
class Gorilla(Mammal):
    """ Gorilla() class is a child class of Mammal() class. It inherits the equal() and listenToMammal()
    functions from parent class. It sets it's instance variables to the specific gorilla genus and to
    grutinting noise. 
    It has accessors getGenus() and makeNoise() that returns their genus and the noise from the class."""
    
    def __init__(self):
        """constructor of Gorilla() class uses the Mammal() class constructor to inherit from parent class. It sets the instance variables
        genus to Gorilla and noise to grunting"""
        Mammal.__init__(self)
        self.genus = "Gorilla"
        self.noise = "grunting"
      
    def getGenus(self):
        """getGenus() function is an accessor that returns the instance variable genus from Gorilla() class."""
        return self.genus
      
    def makeNoise(self):
        """makeNoise() function is an accessor that returns the instance variable noise from Gorilla() class."""
        return self.noise

class Bear(Mammal):
    """Bear() class is a child class of Mammal(). It inherits the equal() and listenToMammal()
    functions from parent class. It sets it's instance variables to the specific bear genus and to
    roaring noise. 
    It has accessors getGenus() and makeNoise() that returns their genus and the noise from the class."""

    def __init__(self):
        """constructor of Bear() class uses the Mammal() class constructor to inherit from parent class. It sets the instance variables
        genus to Bear and noise to roaring"""
        Mammal.__init__(self)
        self.genus = "Bear"
        self.noise = "roaring"
      
    def getGenus(self):
        """getGenus() function is an accessor that returns the instance variable genus from Bear() class."""
        return self.genus
      
    def makeNoise(self):
        """makeNoise() function is an accessor that returns the instance variable noise from Bear() class."""
        return self.noise
      
def main():
    """main() function is the starting point of execution"""
    
    """m variable is an object of the Mammal() class, g variable is an object of the Gorilla()
    class, and b variable is an object of the Bear() class"""
    m = Mammal()
    g = Gorilla()
    b = Bear()
    
    """m.equal(g,b) statement calls the equal() function from Mammal() class with the parameters
    g(a Gorilla() object) and b(a Bear() object) and compares them to each other"""
    m.equal(g, b)
    
    """g2 is an other object of Gorilla() class. Using the equal() method we are comparing both objects.
    Not necessary to the assignment but wanted to show comparing two objects of the same class being compared"""
    g2 = Gorilla()
    m.equal(g, g2)
    
    """b2 is another object of Bear() class. Using the equal() method we are comparing both objects.
    Not necessary to the assignment  but wanted to show two different objects of the same class being compared""" 
    b2 = Bear()
    m.equal(b, b2)
    
    """m.equal(b,g) statement compares b and g objects to each other. Wanted to show polymorphism in the equal() function
    and that the equal() function shows that the parameters being passed are specific to the object class being passed."""
    m.equal(b, g)
    
    """in these 4 statements we use the listenToMammal() function from the parent class for every object
    created (g,b,g2,b2) from the child class to print a noise specific to the child class"""
    g.listenToMammal()
    b.listenToMammal()
    g2.listenToMammal()
    b2.listenToMammal()
      
if __name__ == '__main__':
    main()
