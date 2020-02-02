import React from 'react';
import { Text, View, TextInput, TouchableOpacity, StyleSheet, Button, WebView, AppRegistry, Image, FlatList, ActivityIndicator } from 'react-native';

URL0 = 'https://bloodoath-co.dk/CA3/api/info/all/';
 URL1 = 'https://bloodoath-co.dk/CA3/api/info/all/?format=json';
 URL2 = 'https://swapi.co/api/people/';

export default class JsonData extends React.Component {
 constructor(){
   super();
this.state = {
  text: 'Search name',
  items: []
}
 }
 //'https://swapi.co/api/people/'
componentWillMount(){
return fetch(URL1)
.then( response => response.json())
.then( ({results: items}) => this.setState({items}));
}
update(e){
  this.setState({filter: e.target.value})
}
filter(e){
  this.setState({filter: e.target.value})
}
render(){
let items = this.state.items;
if(this.state.filter){
  items = items.filter( item => item.name.toString().toLowerCase()
  .includes(this.state.filter.toString().toLowerCase()))
}
return ( 
  <View>
    <Text>
      {this.state.txt}
      <Widget filter = {this.filter.bind(this)}/>
      {items.map((item, index) => {return (<Person key = {index} person = {item} />)} )}
    </Text>
  </View>
)
}
}
const Person = (props) => {return(<Text>{props.person.name} {"\n"}</Text>)}

const Widget = (props) =>
<TextInput onChange={props.filter}/>