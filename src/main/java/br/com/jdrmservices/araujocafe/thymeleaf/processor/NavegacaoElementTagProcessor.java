package br.com.jdrmservices.araujocafe.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class NavegacaoElementTagProcessor extends AbstractElementTagProcessor{
	
	private static String NOME_TAG = "navegacao";
	private static final int PRECEDENCIA = 1000;
	
	public NavegacaoElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		IModel model = modelFactory.createModel();
		
		IAttribute url = tag.getAttribute("url");
		IAttribute texto = tag.getAttribute("texto");
		
		model.add(modelFactory.createStandaloneElementTag(
				"th:block",
				"th:replace",
				String.format("layout/fragments/navegacao :: navegacao (%s, '%s')", url.getValue(), texto.getValue())));
		
		structureHandler.replaceWith(model, true);
	}
	
}