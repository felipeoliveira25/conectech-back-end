/* eslint-disable react/prop-types */
import { HeaderDiv, ImagemLogo, ListaItensHeader, ItemHeader, BotaoHamburguer } from './style'
import Botao from '../Botao'
import {useNavigate} from 'react-router-dom'
const Header = ({itens, displayBotao, position})=> {

    const navigate = useNavigate()
     const goToInitialPage = () => {
         navigate('/')
     }
     const goToTelaCadastro = () => {
        navigate('/cadastro')
     }
    return(
        
             <HeaderDiv position={position}>
                <ImagemLogo onClick={goToInitialPage} src='images/img-logo.png'/>

                <ListaItensHeader>
                    {itens.length> 0 && itens.map((item)=> <ItemHeader onClick={item.caminho} key={item.id}>{item.nome}</ItemHeader>)}
                    <Botao onClick={goToTelaCadastro} display={displayBotao}  nome="Vamos começar" link=""/>
                </ListaItensHeader>

                <BotaoHamburguer >
                    <img src='images/img-menu.svg'/>
                </BotaoHamburguer>
            </HeaderDiv>
        
       
    )
}

export default Header