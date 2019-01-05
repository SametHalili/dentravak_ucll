import DenTravakAbstractElement from '../travak-abstract-element.js';

class DenTravakOrderList extends DenTravakAbstractElement {

    constructor() {
        super('travak-admin-app')
    }

    connectedCallback() {
        super.connectedCallback();
        //fetch('http://localhost:8234/den-travak/orders')
        fetch('http://193.191.177.8:10418/den-travak/orders')
            .then(resp => resp.json())
            .then(json => this.updateOrderList(json));
        this.initEventListeners();
    }

    initEventListeners() {
        this.byId('edit-sandwiches-btn').addEventListener('click', (e) => this.app().showSandwichList());
        this.byId('downloadcsv').addEventListener('click', (e) => this.download_csv());
    }

    updateOrderList(orders, printed) {
        if(printed) {
            orders.forEach(order => {
                order.printed = true;
            })
        } 
        else {
            orders.forEach(order => {
                order.printed = false;
            })
        }
        let orderList = this.byId('orders');
        orderList.innerHTML = ``;
        orders.forEach(order => {
            let orderEl = htmlToElement(this.getOrderTemplate(order));
            orderList.appendChild(orderEl);
        });
    }


    download_csv() {        
        fetch('http://193.191.177.8:10418/den-travak/orders')
            .then(resp => resp.json())
            .then(json => {
                var data = []
                for(var i = 0; i < json.length; i++) {
                    data.push([json[i].id, json[i].sandwichId,json[i].name, json[i].breadType,json[i].price, json[i].mobilePhoneNumber, json[i].creationDate]);
                }
                var separate = 'sep=,\r\n';
                var csv = separate + 'ID,SandwichID,Name,BreadType,Price,MobilePhoneNumber,CreationDate\n';
                data.forEach(function(row) {
                    csv += row.join(',');
                    csv += "\n";
                });
                var hiddenElement = document.createElement('a');
                hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
                hiddenElement.target = '_blank';
                hiddenElement.download = 'orders.csv';
                hiddenElement.click();
                this.updateOrderList(json, true);
            });
            
    }

    get template() {
        return `
            <style>
                div.dt-order-info {
                    margin-left: auto;
                }
                .bmd-list-group-col {
                    width: 70%;
                }
                p.list-group-item-heading {
                    display:flex;
                    justify-content: space-between;
                }
                span.creationDate {
                    display:inline-block;
                    float: right;
                }
                .travak-header {
                    display: flex;
                }
                .travak-header button {
                    margin-left: auto;
                }
            </style>
            <div class="animate">
                <div class="travak-header">
                    <h4>Den Travak Bestellingen</h4>
                    <button id="edit-sandwiches-btn" type="button" class="btn btn-primary">Bewerk broodjeslijst</button>
                </div>
                <div>
                <ul id="orders" class="list-group">
                </ul>
                </div>
                <button type="button" class="btn btn-primary" id="downloadcsv">
                Download CSV
                </button>
            </div>
        `;
    }

    getOrderTemplate(order) {
        return `
        <div class="card">
            <div class="card-body">
                <a class="list-group-item">
                    <button type="button" class="btn btn-primary bmd-btn-fab">
                        ${order.name.charAt(0)}
                    </button>
                    <div class="bmd-list-group-col">
                        <p class="list-group-item-heading">Telefoon nummer: ${order.mobilePhoneNumber}
                            <span class="creationDate">${dateFns.distanceInWordsToNow(order.creationDate)} ago</span>
                            <span> Printed: ${order.printed}</span>
                        </p>
                        <p class="list-group-item-text">Bestelling: ${order.name} - ${order.breadType.toLowerCase()}</p>
                    </div>
                    <div class="dt-order-info">
                        <p class="list-group-item-text">Prijs: €${order.price}</p>
                    </div>
                </a>
            </div>
        </dvi>
        `;
    }
}

customElements.define('travak-order-list', DenTravakOrderList);