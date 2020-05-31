package com.todo;

import com.todo.layout.HomeLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
@ParentLayout(HomeLayout.class)
public class GlobalExecptionHandler extends Component implements HasErrorParameter<Exception> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<Exception> parameter) {
        getElement().setText(
                "Sorry! There was an exception to render this page.");
        return HttpServletResponse.SC_EXPECTATION_FAILED;
    }
}




