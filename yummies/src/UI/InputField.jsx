import React from "react";
import { Input, Form, TextArea } from "semantic-ui-react";
import { isNull, isEmpty } from "lodash";

const InputField = props => {
    const showErrors =_=>{
        // return (typeof(props.value) === 'String') && isEmpty(props.value) && props.error
        if(props.error && props.error.indexOf("wrongFormat") >= 0){
            return props.error
        }
        return (isEmpty(props.value) && props.error) && props.error
    }

    return (
        <div>
            <div className="inputField-label">
                {props.inputlabel}{props.required && '*'}
            </div>
            {props.textarea ?
                <Form>
                    <TextArea
                        className="inputField-textArea"
                        {...props} 
                        textarea={''}
                        value={isNull(props.value) || typeof props.value === "undefined"  ? "" : props.value}
                        fluid={''}
                        style={{ minHeight: 105 }}
                        
                    />
                </Form>
            :
            <React.Fragment>
                <Input
                    autoComplete={props.password ? "new-password" : "off"}
                    {...props}
                    textarea={''}
                    value={isNull(props.value) || typeof props.value === "undefined"  ? "" : props.value}
                    error={showErrors()}
                >
                </Input>
                </React.Fragment>
                }
                {showErrors() && <div className='inputField-error'>{showErrors()}</div>}
        </div>
    );
};

InputField.defaultProps = {
  inputlabel: '',
  disabled: false,
  error: false,
  fluid: true,
  required: false,
  textarea: false
};

export default InputField;
