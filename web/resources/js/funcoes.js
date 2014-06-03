/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function() {    
    jQuery(".monetario").maskMoney({symbol:"R$", decimal:",", thousands:"."});
    jQuery(".data").mask('00/00/0000');
    
    jQuery("#txtDesconto").maskMoney({symbol: "R$", decimal: ",", thousands: "."});
    jQuery("#txtValorRecebido").maskMoney({symbol: "R$", decimal: ",", thousands: "."});    
    
    jQuery("#txtDescontoMensalista").maskMoney({symbol: "R$", decimal: ",", thousands: "."});
    jQuery("#txtValorRecebidoMensalista").maskMoney({symbol: "R$", decimal: ",", thousands: "."});
    
});

function visualizarRelatorio() {
   //location.href="/engegraphi/relatorioCartorio"
   window.open('https://www.cnj.jus.br/bnmp/','Pesquisa','width=800,height=600,left=100,top=50,screenX=0,screenY=100')
}