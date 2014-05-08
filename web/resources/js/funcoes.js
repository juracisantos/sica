/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function () {
    //jQuery("#txtValorCobrado").maskMoney({symbol:"R$", decimal:",", thousands:"."});
    jQuery("#txtDesconto").maskMoney({symbol:"R$", decimal:",", thousands:"."});
    jQuery("#txtValorRecebido").maskMoney({symbol:"R$", decimal:",", thousands:"."});
    jQuery("#txtTroco").maskMoney({symbol:"R$", decimal:",", thousands:"."});   
});

function visualizarRelatorio() {
   //location.href="/engegraphi/relatorioCartorio"
   window.open('https://www.cnj.jus.br/bnmp/','Pesquisa','width=800,height=600,left=100,top=50,screenX=0,screenY=100')
}

