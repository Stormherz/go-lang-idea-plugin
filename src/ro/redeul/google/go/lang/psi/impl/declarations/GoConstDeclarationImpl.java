package ro.redeul.google.go.lang.psi.impl.declarations;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.annotations.NotNull;
import ro.redeul.google.go.lang.lexer.GoTokenTypes;
import ro.redeul.google.go.lang.psi.declarations.GoConstDeclaration;
import ro.redeul.google.go.lang.psi.expressions.GoExpr;
import ro.redeul.google.go.lang.psi.expressions.literals.GoLiteralIdentifier;
import ro.redeul.google.go.lang.psi.impl.GoPsiElementBase;
import ro.redeul.google.go.lang.psi.types.GoType;
import ro.redeul.google.go.lang.psi.visitors.GoElementVisitor;

public class GoConstDeclarationImpl extends GoPsiElementBase
    implements GoConstDeclaration {

    public GoConstDeclarationImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean hasInitializers() {
        return findChildByType(GoTokenTypes.oASSIGN) != null;
    }

    @Override
    public GoLiteralIdentifier[] getIdentifiers() {
        return findChildrenByClass(GoLiteralIdentifier.class);
    }

    @Override
    public GoType getIdentifiersType() {
        return findChildByClass(GoType.class);
    }

    @Override
    @NotNull
    public GoExpr[] getExpressions() {
        return findChildrenByClass(GoExpr.class);
    }


    @Override
    public void accept(GoElementVisitor visitor) {
        visitor.visitConstDeclaration(this);
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor,
                                       @NotNull ResolveState state,
                                       PsiElement lastParent,
                                       @NotNull PsiElement place) {
        return processor.execute(this, state);
    }
}
