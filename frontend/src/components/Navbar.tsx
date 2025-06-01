const Navbar = () => {
  return (
    <nav className="sticky top-0 z-50 w-full bg-primary backdrop-blur-lg px-6 md:px-16 py-4 flex flex-col sm:flex-row flex-wrap items-center justify-between">
      {/* Logo */}
      <a href="/" aria-label="Home" className="no-underline">
        <img src="logo.svg" width="97" height="24" alt="Logo" loading="lazy" />
      </a>

      {/* Navigation Links */}
      <div className="mt-4 sm:mt-0 flex sm:flex-row gap-4 sm:gap-6 items-center text-[15px] font-figtree text-white/50">
        <a
          href="/terms-of-service"
          className="transition-colors duration-200 hover:text-white/40"
        >
          Terms of Service
        </a>

        <a
          href="/privacy-policy"
          className="transition-colors duration-200 hover:text-white/40"
        >
          Privacy Policy
        </a>

        {/* Only show Book a Demo on medium+ screens */}
        <a
          href="/book-a-demo"
          className="hidden md:inline-block px-4 py-2 rounded-sm bg-white/10 text-white hover:bg-white/5 hover:text-white/80 transition-all duration-200"
        >
          Book a Demo
        </a>
      </div>
    </nav>
  );
};

export default Navbar;
