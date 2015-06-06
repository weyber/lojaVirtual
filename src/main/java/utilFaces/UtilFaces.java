package utilFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class UtilFaces {

    private UtilFaces() {

    }

    public static void save(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem));
    }
}
