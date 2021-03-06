package nodes3;

import asm.ASMGeneratorVisitor;

import java.util.List;

//⟨Label3⟩ :
//| if ( ⟨RelExp3Impl⟩ ) goto ⟨Label3⟩ ;
//| goto ⟨Label3⟩ ;
//| readln ( ⟨id3⟩ ) ;
//| println ( ⟨idc3⟩ ) ;
//| ⟨Type3⟩ ⟨id3⟩ = ⟨Exp3Impl⟩ ; i never produce this actually. I only declare vars at the beginning
//| ⟨id3⟩ = ⟨Exp3Impl⟩ ;
//| ⟨id3⟩.⟨id3⟩ = ⟨Exp3Impl⟩ ;
//| ⟨id3⟩( ⟨VList3⟩ ) ;
//| return ⟨id3⟩ ; | return ;
public class Stmt3 extends Node3 {
    public Stmt3Type stmtType;
    public String string;
    public Id3 id3_1;
    public Id3 id3_2;
    public Exp3 exp3Impl;
    public Type3 vartype;
    public Idc3 idc3;
    public List<Idc3> params;

    String print = "";

    public Stmt3(Stmt3Type stmtType, String string) {
        if (!(stmtType.equals(Stmt3Type.LABEL) || stmtType.equals(Stmt3Type.GOTO)))
            System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.string = string;
        if (stmtType.equals(Stmt3Type.GOTO)) print = "goto " + string;
        else print = "Label: " + string;

    }

    public Stmt3(Stmt3Type stmtType, String string, Exp3 relExp3) {
        if (!(stmtType.equals(Stmt3Type.IF))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.string = string;
        this.exp3Impl = relExp3;
        print = "if (" + relExp3 + ") goto " + string;
    }

    public Stmt3(Stmt3Type stmtType, Id3 id3_1) {
        this.stmtType = stmtType;
        this.id3_1 = id3_1;
        if ((stmtType.equals(Stmt3Type.READLN))) {
            print = "readln(" + id3_1 + ")";
        } else if (stmtType.equals(Stmt3Type.RETURN_VAR)) {
            print = "return " + id3_1;
        } else {
            System.err.println("WARNING: wrong init Stmt3");
        }

    }

    public Stmt3(Stmt3Type stmtType, Idc3 idc3) {
        if (!(stmtType.equals(Stmt3Type.PRINTLN))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.idc3 = idc3;
        print = "println(" + idc3 + ")";
    }

    public Stmt3(Stmt3Type stmtType, Type3 type, Id3 varName, Exp3 exp3Impl) {
        if (!(stmtType.equals(Stmt3Type.ASS_VARDECL))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.exp3Impl = exp3Impl;
        this.vartype = type;
        this.id3_1 = varName;
        print = "" + type + " " + varName + " = " + exp3Impl;
    }


    public Stmt3(Stmt3Type stmtType, Id3 id3_1, Exp3 exp3Impl) {
        if (!(stmtType.equals(Stmt3Type.ASS_VAR))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.id3_1 = id3_1;
        this.exp3Impl = exp3Impl;
        print = "" + id3_1 + " = " + exp3Impl;
    }

    public Stmt3(Stmt3Type stmtType, Id3 id3_1, Id3 id3_2, Exp3 exp3Impl) {
        if (!(stmtType.equals(Stmt3Type.ASS_FIELD))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.id3_1 = id3_1;
        this.id3_2 = id3_2;
        this.exp3Impl = exp3Impl;
        print = "" + id3_1 + "." + id3_2 + " = " + exp3Impl;
    }

    public Stmt3(Stmt3Type stmtType, Exp3Impl atomFunctionCall) {
        if (!(stmtType.equals(Stmt3Type.FUNCTION))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        this.exp3Impl = atomFunctionCall;
        print = atomFunctionCall.toString();
    }

    public Stmt3(Stmt3Type stmtType) {
        if (!(stmtType.equals(Stmt3Type.RETURN))) System.err.println("WARNING: wrong init Stmt3");
        this.stmtType = stmtType;
        print = "return";
    }


    @Override
    public String toString() {
        return print;
    }

    public enum Stmt3Type {
        LABEL,
        GOTO,
        IF,
        READLN,
        PRINTLN,
        ASS_VARDECL,
        ASS_VAR,
        ASS_FIELD,
        FUNCTION,
        RETURN,
        RETURN_VAR;

        public boolean equals(Stmt3Type other){
            return this.name().equals(other.name());
        }
    }

    public boolean equals(Stmt3Type other) {
        return this.stmtType.name().equals(other.name());
    }

    public int accept(ASMGeneratorVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
