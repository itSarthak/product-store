import { motion } from "motion/react"

const HomePage = () => {
  return (
    <div className=' bg-primary'>
      <motion.h1 className="text-3xl sm:text-4xl md:text-5xl lg:text-5xl xl:text-5xl font-figtree tracking-tight leading-tight sm:leading-[1.2]   max-w-full px-4 sm:px-0 opacity-100" 
      initial={{y:50, opacity:0}}
      animate={{y:0, opacity:1}}
      transition={{duration:0.5}}>
        <span className="max-w-[100%] display-block text-transparent bg-clip-text" style={{
          backgroundImage: "linear-gradient(90deg, #fff 72%, #f36176)",
        }}>
          Take Back Control of
          <br />
          Your Product development
        </span>
      </motion.h1>
    </div>
  )
}

export default HomePage