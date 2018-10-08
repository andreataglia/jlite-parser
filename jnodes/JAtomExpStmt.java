package jnodes;

// atom LPAREN expList RPAREN SEMICOLON
public class JAtomExpStmt extends JStmt {
    public JAtom atom;
    public JExpList expList;

    public JAtomExpStmt(JAtom atom, JExpList expList) {
        this.atom = atom;
        this.expList = expList;
    }

    @Override
    public String toString() {
        return atom + "(" + expList + ");\n";
    }
}