import java.util.*;

public class Sphere{
    public Vec3 c;
    public double r;
    public Vec3 color;
    
    public Sphere(Vec3 c, double r,Vec3 cl){
        this.c = c;
        this.r=r;
        this.color=cl;
    }
    
    double intersection(Vec3 eye, Vec3 v){
        Vec3 Q = Vec.sub(eye,c);
        double A = Vec.dot(v,v);
        double B=2.0*Vec.dot(v,Q);
        double C=Vec.dot(Q,Q)-r*r;
                    
        double disc = B*B-4.0*A*C;
        if( disc < 0.0 )
            return -1.0;
        disc = Math.sqrt(disc);
        double A2=2.0*A;
        double t1 = (-B + disc) / A2;
        double t2 = (-B - disc) / A2;
        double t;
                    
        if( t1 < 0 && t2 < 0 )
            return -1.0;
        else if( t1 < 0 )
            t=t2;
        else if( t2 < 0 )
            t=t1;
        else if( t1 < t2 )
            t=t1;
        else
            t=t2;

        return t;
    }
}
