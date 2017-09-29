public class Tuple {
  private Object[] args;
  public Tuple(Object... a){
    args = a;
  }
  public <T> T get(int i){
    if(i >= args.length || i < 0) throw new IndexOutOfBoundsException();
    return (T) args[i];
  }
  public int size(){
    return this.args.length;
  }
  public String toString() {
    String ret = "(";
    for(Object a : args) {
      ret += a.toString() + ", ";
    }
    ret = ret.substring(0, ret.length()-2);
    ret += ")";
    return ret;
  }
  public String toStringWithClass() {
    String ret = "(";
    for(Object a : args) {
      ret += a.toString() + "<" + a.getClass().getSimpleName() +">, ";
    }
    ret = ret.substring(0, ret.length()-2);
    ret += ")";
    return ret;
  }
  public boolean equals(Tuple t){
    if(this.args.length != t.args.length) return false;
    for(int i = 0; i < this.args.length; i++){
      if(!this.args[i].equals(t.args[i])) return false;
    }
    return true;
  }
  public boolean equals(Object o){
    return this.hashCode() == o.hashCode();
  }
  public int hashCode(){
    return this.toStringWithClass().hashCode();
  }
}
