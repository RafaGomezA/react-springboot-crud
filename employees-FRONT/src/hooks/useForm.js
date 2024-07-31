import { useState } from 'react'

export const useForm = (formularioInicial) => {

    const [formState, setFormState] = useState(formularioInicial)

    const onInputChange = ({target}) => { 
        const {name, value} = target
        setFormState ( {...formState, [name]: value} )                               
    }

  return {
    formState,
    setFormState,
    onInputChange
  }
}