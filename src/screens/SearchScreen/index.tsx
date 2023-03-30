import { View, Text,Pressable} from 'react-native'
import React,{useEffect, useState} from 'react'
import SearchBar from './components/SearchBar/SearchBar';
import { FlatList } from 'react-native-gesture-handler';
import styles from './styles';
import axios from 'axios';
import { useAppSelector } from '../../redux/features/hooks';
import { selectToken } from '../../redux/features/tokenSlice';
import { CategoryType, PostType } from '../../types/types';
import Category from '../../components/Category';
//@ts-ignore
import {API_URL} from "@env"
import { useNavigation } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import { RootStackParamList } from '../../navigation';
import Post from '../../components/Post';


export default function SearchScreen() {
  const navigation = useNavigation<StackNavigationProp<RootStackParamList>>();
  const [posts,setPosts]  = useState<PostType[] | string>('');
  const getToken = useAppSelector(selectToken);
 const [search,setSearch] = useState<string>("");
 const [categories,setCategories] = useState<CategoryType[]>();
 const getCategories = ()=>{
  axios.get(`${API_URL}/api/categories/`,{
    headers: {
      Authorization: `Bearer ${getToken.token}`
    }
  }).then(res=>
    {
      setCategories(res.data);
     console.log(res.data)

    }).catch(e=>console.log(e));
 }
 const searchTitle = ()=>{
  axios.get(`${API_URL}/api/posts/search/${search}`,{
    headers: {
      Authorization: `Bearer ${getToken.token}`
    }
  }).then(res=>
    {
      setPosts(res.data);
     console.log(res.data)

    }).catch(e=>console.log(e));
 
 }
 useEffect(() => {
   
 getCategories();
   
   }
 , [])
  return (
    <View>
    <SearchBar search={search} setSearch={setSearch} searchTitle={searchTitle}/>
    {
      typeof posts=='string' || posts.length==0? 
    <View style={styles.flatListContainer}>
 <View style={styles.headingContainer}>
  <Text style={styles.heading}>Categories</Text>
 </View>
    <FlatList 
    data={categories}
    renderItem={({item}: {item: CategoryType}):JSX.Element=> 
    (
      <Pressable style={styles.postContainer} onPress={()=>[navigation.navigate("PostsByCategory",{id: item.categoryId as string|number})]}>
    <Category categoryId={item.categoryId} categoryDescription={item.categoryDescription} categorytitle={item.categorytitle}/>
        </Pressable>
    )
  }
  keyExtractor={(item:CategoryType,index:number):string=>index.toString()}
  numColumns={2}
  showsHorizontalScrollIndicator={false}
  showsVerticalScrollIndicator={false}
  />

  </View> : 
  <View>
    
    
    <FlatList 
    data={posts}
    renderItem={({item}: {item: PostType}):JSX.Element=> 
    (
      <View>
    <Post  id={item.postId} imageSource={item.imageName} date={item.addedDate} title={item.title} content={item.content}/>
        </View>
    )
  }
  keyExtractor={(item:PostType,index:number):string=>index.toString()}
  showsHorizontalScrollIndicator={false}
  showsVerticalScrollIndicator={false}
  
  />
    
    
     </View>
    }
    </View>
  )
}
