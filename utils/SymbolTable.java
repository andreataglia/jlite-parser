package utils;

import concrete_nodes.ClassDecl;
import concrete_nodes.MethodDecl;
import concrete_nodes.VarDecl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class SymbolTable {
    int indentLevel;
    List<ClassDescriptor> classDescriptors;
    private HashMap<Integer, Stack<VarDecl>> enviromentVars;
    ClassNameType currentClass;
    String currentMethod;

    SymbolTable() {
        this.indentLevel = 0;
        enviromentVars = new HashMap<>();
        classDescriptors = new ArrayList<>();
    }

    public void setClassDescriptors(List<ClassDescriptor> classDescriptors) {
        this.classDescriptors = classDescriptors;
    }

    void setCurrentClass(ClassDecl classDecl) {
        this.currentClass = classDecl.className;
    }

    void increaseIndentLevel() {
        indentLevel++;
        enviromentVars.put(indentLevel, new Stack<>());
    }

    void decreaseIndentLevel() {
        indentLevel--;
        enviromentVars.remove(indentLevel);
    }

    void increaseIndentLevel(MethodDecl methodDecl) {
        increaseIndentLevel();
        currentMethod = methodDecl.name;
        for (VarDecl v : methodDecl.params.list) {
            pushLocalVar(v);
        }
    }

    void increaseIndentLevel(ClassNameType classNameType) {
        increaseIndentLevel();
        currentClass = classNameType;
    }

    void pushLocalVar(VarDecl varDecl) {
        enviromentVars.get(indentLevel).push(varDecl);
    }

    void popLocalVar() {
        enviromentVars.get(indentLevel).pop();
    }


    boolean fieldIsInClass(String field, String className) {
        for (ClassDescriptor c : classDescriptors) {
            if (c.className.equals(className)) {
                if (c.getFieldType(field) != null) return true;
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////
    ////////////////////////////LookUps///////////////////////////////////////////


    BasicType lookupClassFieldType(ClassNameType classNameType, String id) {
        for (ClassDescriptor c : classDescriptors) {
            if (c.className.equals(classNameType)) {
                return c.getFieldType(id);
            }
        }
        return null;
    }

    BasicType lookupVarType(String id) {
        for (int i = indentLevel; i > 0; i--) {
            if (enviromentVars.get(indentLevel) != null) {
                for (VarDecl var : enviromentVars.get(indentLevel)) {
                    if (var.id.equals(id)) return var.type;
                }
            }
        }
        return null;
    }

    FunctionType lookupFunctionType(String id) {
        for (ClassDescriptor c: classDescriptors) {
            for (MethodSignature m: c.methodSignatures) {
                if (m.name.equals(id)) return m.getFunctionType();
            }
        }
        return null;
    }

    ClassNameType lookUpClass(String className) {
        for (ClassDescriptor c : classDescriptors) {
            System.out.println(c.className);
            if (c.className.name.equals(className)) return c.className;
        }
        return null;
    }
}
