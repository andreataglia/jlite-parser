class Main {
    Void main(){
        Int a;
        Int b;
        Int i;
        Int d;
        Int t1;
        Int t2;
        Compute help;
        a = 1;
        b = 2;
        i = 3;
        d = 4;
        help = new Compute();
        t1 = help.addSquares(a,b) + help.square(i);
        t2 = help.square(d); // Should be equal to 16
            //if(t2>t1){
        println(t1);
        println(t2);
            // Should be the output
            //}
            //else{
            //println("Square of d smaller than sum of squares");
            //}
    }
}
class Compute {
    Bool computedSquares;
    Int cachedValue;
    Int square(Int a){
        return a*a;
    }
    Int add(Int a, Int b){
        return a+b;
    }
    Int addSquares(Int a, Int b){
            //if(computedSquares){
            //return cachedValue;
            //}
            //else{
            //computedSquares = true;
        return add(square(a),square(b));
            //}
    }
}
