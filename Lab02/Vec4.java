public class Vec4{
    public Vec4(double x, double y, double z, double w){
        this.x_=x;
        this.y_=y;
        this.z_=z;
        this.w_=w;
    }

    public Vec4(){
        x_=y_=z_=w_=0.0;
    }
    
    public Vec4 add(Vec4 v){
        return new Vec4(x_+v.x_,y_+v.y_,z_+v.z_,w_+v.w_);
    }
    
    public Vec4 sub(Vec4 v){
        return new Vec4(x_-v.x_,y_-v.y_,z_-v.z_,w_-v.w_);
    }
    
    public Vec4 mul(double d){
        return new Vec4(d*x_,d*y_,d*z_,d*w_);
    }
    
    public double dot(Vec4 v){
        return x_*v.x_+y_*v.y_+z_*v.z_+w_*v.w_;
    }

    public double length(){
        return Math.sqrt(this.dot(this));
    }
    
    public Vec4 normalize(){
        return this.mul(1.0/length());
    }

    public double x(){ return x_; }
    public double y(){ return y_; }
    public double z(){ return z_; }
    public double w(){ return w_; }
    
    private double x_,y_,z_,w_;
}

