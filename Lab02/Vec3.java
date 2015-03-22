public class Vec3{
    public Vec3(double x, double y, double z){
        this.x_=x;
        this.y_=y;
        this.z_=z;
    }

    public Vec3(){
        x_=y_=z_=0.0;
    }
    
    public Vec3 add(Vec3 v){
        return new Vec3(x_+v.x_,y_+v.y_,z_+v.z_);
    }
    
    public Vec3 sub(Vec3 v){
        return new Vec3(x_-v.x_,y_-v.y_,z_-v.z_);
    }
    
    public Vec3 mul(double d){
        return new Vec3(d*x_,d*y_,d*z_);
    }
    
    public double dot(Vec3 v){
        return x_*v.x_+y_*v.y_+z_*v.z_;
    }

    public double length(){
        return Math.sqrt(this.dot(this));
    }
    
    public Vec3 normalize(){
        return this.mul(1.0/length());
    }

    public double x(){ return x_; }
    public double y(){ return y_; }
    public double z(){ return z_; }
    
    public double x_,y_,z_;
}

