import React from 'react';
import { Text, View, Platform, TouchableOpacity, StyleSheet, Button, WebView, ScrollView } from 'react-native';
import { Constants, WebBrowser } from "expo";
import { createStackNavigator  } from 'react-navigation';
import JsonData from './JsonData'

const Touchable = (props) => (
  <TouchableOpacity style={styles.button} onPress={props.onPress}>
    <Text style={styles.buttonText}>{props.title}</Text>
  </TouchableOpacity>)

class HomeScreen extends React.Component {
  static navigationOptions = { title: 'Day1 Tutorial' };
  render() {
    const { navigate } = this.props.navigation;
    return (
      <ScrollView style={{ backgroundColor: 'black'}}>
        <Text style={{ textAlign: "center", fontSize: 20, color: 'white' }}>Welcome to the most awesome teachers!</Text>
        <Text style={{ fontSize: 20, color: 'white' }}>This was created to show a simple react native app,</Text>
        <Text style={{ fontSize: 20, color: 'white' }}>which we hope you like ...</Text>
        <Touchable onPress={() => navigate('jsondata')} title="JsonData" />
      </ScrollView>
    )
  }
}
export default App = () => <RouteStack style={{ marginTop: Platform.OS === 'ios' ? 0 : Constants.statusBarHeight / 2 }} />

const RouteStack = createStackNavigator({
  Home: { screen: HomeScreen },
  jsondata: { screen: JsonData}

});

const styles = StyleSheet.create({
  button: {
    margin: 3,
    alignItems: 'center',
    backgroundColor: '#2196F3'
  },
  buttonText: {
    padding: 7,
    fontSize: 18,
    fontWeight: "bold",
    color: 'white'
  }
})


