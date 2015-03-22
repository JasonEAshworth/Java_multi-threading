import java.util.*;

public  class Vec{
    public static Vec3 add(Vec3 a, Vec3 b){
        return a.add(b);
    }
    public static Vec4 add(Vec4 a, Vec4 b){
        return a.add(b);
    }
    
    public static Vec3 sub(Vec3 a, Vec3 b){
        return a.sub(b);
    }
    public static Vec4 sub(Vec4 a, Vec4 b){
        return a.sub(b);
    }

    public static Vec3 read(Scanner in){
        double x,y,z;
        x=in.nextDouble();
        y=in.nextDouble();
        z=in.nextDouble();
        return new Vec3(x,y,z);
    }
    
    public static Vec3 mul(Vec3 a, double b){
        return a.mul(b);
    }
    public static Vec4 add(Vec4 a, double b){
        return a.mul(b);
    }

    public static Vec3 mul(double a, Vec3 b){
        return b.mul(a);
    }
    public static Vec4 mul(double a, Vec4 b){
        return b.mul(a);
    }
    
    public static double dot(Vec3 a, Vec3 b){
        return a.dot(b);
    }
    public static double dot(Vec4 a, Vec4 b){
        return a.dot(b);
    }
    
    public static double length(Vec3 a){
        return a.length();
    }
    public static double length(Vec4 a){
        return a.length();
    }
    
    public static Vec3 normalize(Vec3 a){
        return a.normalize();
    }
    public static Vec4 normalize(Vec4 a){
        return a.normalize();
    }
}

