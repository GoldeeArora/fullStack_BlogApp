import { View, Text,TextInput,Pressable } from 'react-native'
import React,{useState} from 'react'
import styles from './style'
import Icon from "react-native-vector-icons/Ionicons"
export default function SearchBar({ search,setSearch,searchTitle}: {search: string,setSearch : React.Dispatch<React.SetStateAction<string>>,searchTitle:any;}) {
  return (
    <View style = {styles.searchBarContainer}>
    <TextInput 
    style={styles.input} 
    placeholder="Search Blogs" 
    onChangeText={(text: string)=> setSearch(text)} 
    value={search}/>
    <Pressable style={styles.iconContainer} onPress={()=>searchTitle()}>
<Icon name="search" size={28}/>
    </Pressable>
  </View>
  )
}