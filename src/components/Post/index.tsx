import { View, Text,Pressable,Image } from 'react-native'
import React from 'react'
import { useNavigation } from '@react-navigation/native'
import { StackNavigationProp } from '@react-navigation/stack';
import { RootStackParamList } from '../../navigation';
import { styles } from './styles';
interface Props{
  id: string|number
    imageSource: string,
    title: string,
    date: Date|string,
    content: string,

}
export default function Post({id,imageSource,title,date,content}: Props): JSX.Element {
  const navigation = useNavigation<StackNavigationProp<RootStackParamList>>();

  let formatteddate: string = '';
  if(typeof date === 'string')
  formatteddate = (date.split("T")[0]);


  return (
    <Pressable onPress={()=>navigation.navigate('Post',{
      id:id,
      imageSource: imageSource,
      title:title,
      date:date,
      content:content
    }) } style={styles.container}>
      <View style={styles.imageContainer}>

      <Image source = {{uri: imageSource}} style={styles.image}/>
      </View>
       <View>
        <Text>{title}</Text>
        <Text>{formatteddate}</Text>
        
       </View>
    </Pressable>
  )
}