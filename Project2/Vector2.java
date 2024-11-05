
/**
 * Vector class
 * Includes framework with functions to build physics based jump
 */
public class Vector2
{
    public double x; 
    public double y; 
    /**
     * Constructor for objects of class Vector2
     * 
     */
    public Vector2(double x, double y)
    {
        this.x = x; 
        this.y = y;
    }
    /**
     * Multiply this and another vector, return resulting vector
     */
    public Vector2 multiply(Vector2 otherVector){
        return new Vector2(this.x * otherVector.x, this.y * otherVector.y);
    }
    /**
     * Add this and another vector, return resulting vector
     */
    public Vector2 add(Vector2 otherVector){
        return new Vector2(this.x + otherVector.x, this.y + otherVector.y);
    }
    /**
     * Subtract this and another vector, return resulting vector
     */
    public Vector2 subtract(Vector2 otherVector){
        return new Vector2(this.x - otherVector.x, this.y -  otherVector.y);
    }
    /**
     * Return magnitude of this vector
     */
    public double magnitude(){
        return Math.sqrt(this.x * this.x + this.y *this.y);
    }
    /**
     * toString method
     */
    public String toString(){
        return "x {} " + this.x + " y {} " + this.y; 
    }
}
