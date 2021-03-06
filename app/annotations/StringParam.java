package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import actions.StringParamAction;
import play.mvc.With;

/**
 * 
 * Annotation which use StringParam action
 * @author Jean BOUDET
 *
 */
@With(StringParamAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface StringParam 
{
	String value();
}
