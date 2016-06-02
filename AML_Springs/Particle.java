import org.opensourcephysics.display.Circle;

/*
 * Annabel Strauss and Cal Lavicka
 * March/April 2015
 * Comp Sci X
 * Springs 
 * Version 1.4
 */
public class Particle extends Circle {

  // declarations
  double mass;
  double radius;
  double angle;
  double velocity;
  double vx;
  double vy;
  double xpos;
  double ypos;
  double xacc;
  double yacc;
  double timestep = 1000;
  double k;

  double originalDistance;

  // getters and setters
  public double getMass() {
    return mass;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getAngle() {
    return angle;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public double getVelocity() {
    return velocity;
  }

  public void setVelocity(double velocity) {
    this.velocity = velocity;
  }

  public double getVx() {
    return vx;
  }

  public void setVx(double vx) {
    this.vx = vx;
  }

  public double getVy() {
    return vy;
  }

  public void setVy(double vy) {
    this.vy = vy;
  }

  public double getXpos() {
    return xpos;
  }

  public void setXpos(double xpos) {
    this.xpos = xpos;
  }

  public double getYpos() {
    return ypos;
  }

  public void setYpos(double ypos) {
    this.ypos = ypos;
  }

  public double getXacc() {
    return xacc;
  }

  public void setXacc(double xacc) {
    this.xacc = xacc;
  }

  public double getYacc() {
    return yacc;
  }

  public void setYacc(double yacc) {
    this.yacc = yacc;
  }

  public double getTimestep() {
    return timestep;
  }

  public void setTimestep(double timestep) {
    this.timestep = timestep;
  }

  public void step(double timestep, Particle left, Particle right) {
    double ydist = left.getY() - getY();
    double xdist = left.getX() - getX();
    double distance = Math.sqrt(xdist * xdist + ydist * ydist);

    double forceleft = k * (distance - originalDistance);

    double forcex = forceleft * xdist / distance;
    double forcey = forceleft * ydist / distance;

    //    double forcex = xdist * k;
    //    double forcey = ydist * k;

    if (right != null) {
      ydist = right.getY() - getY();
      xdist = right.getX() - getX();
      distance = Math.sqrt(xdist * xdist + ydist * ydist);

      double forceright = k * (distance - originalDistance);

      forcex += forceright * xdist / distance;
      forcey += forceright * ydist / distance;

    }

    double accx = forcex / mass;
    double accy = forcey / mass;

    setXacc(accx);
    setYacc(accy);

    setVx(getVx() + getXacc() * timestep);
    setVy(getVy() + getYacc() * timestep);
  }

  public void updatePosition() {
    setX(getX() + getVx() * timestep);
    setY(getY() + getVy() * timestep);
  }

}// class
