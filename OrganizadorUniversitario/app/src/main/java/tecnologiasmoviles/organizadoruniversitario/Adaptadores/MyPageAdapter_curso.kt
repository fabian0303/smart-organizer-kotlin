package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentCurso
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentHorario
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentNota
import tecnologiasmoviles.organizadoruniversitario.my_fragment.fragment_home_curso.AudioFragment
import tecnologiasmoviles.organizadoruniversitario.my_fragment.fragment_home_curso.Imagenes_Fragment

class MyPageAdapter_curso  (fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                // creamos fragment
                Imagenes_Fragment()
            }

            else->{
                return AudioFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Imagenes"
            else-> {return "Audio"}
        }
    }
}